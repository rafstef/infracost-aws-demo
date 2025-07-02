provider "aws" {
  region = "eu-central-1"
}

terraform {
  backend "s3" {
    bucket         = "infratest-demo-tfstate"
    key            = "terraform.tfstate"
    region         = "eu-west-1"
  }
}


module "vpc" {
  source = "terraform-aws-modules/vpc/aws"
  name = "infracost-vpc-${var.env}"
  cidr = "${var.cidr}"
  azs             = ["eu-central-1a", "eu-central-1b", "eu-central-1c"]
  private_subnets = var.private_subnets
  public_subnets  = var.public_subnets
  enable_nat_gateway = false
  enable_vpn_gateway = false
  tags = {
    Terraform = "true"
    Environment = "${var.env}"
  }
}

resource "aws_instance" "vm" {
  count         = var.vm_count
  ami           = var.ami
  instance_type = var.instance_type
  subnet_id     = element(module.vpc.private_subnets, count.index % length(module.vpc.private_subnets))
  vpc_security_group_ids = [aws_security_group.vm_sg.id] 

  tags = {
    Name = "infracost-demo-vm-${var.env}-${count.index}"
    Environment = "${var.env}"
    Terraform = "true"
  }
}

resource "aws_security_group" "vm_sg" {
  name        = "infracost-demo-vm-security-group-${var.env}"
  description = "Allow SSH and HTTP"
  vpc_id      = module.vpc.vpc_id
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "infracost-demo-vm-security-group-${var.env}"
    Environment = "${var.env}"
    Terraform = "true"
  }
}
