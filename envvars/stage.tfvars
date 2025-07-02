vm_count      = 6
instance_type = "t3.micro"
ami           = "ami-0929684ff3ecf3f6d"
env           = "Stage"
private_subnets = ["10.0.8.0/24", "10.0.9.0/24", "10.0.10.0/24"]
public_subnets  = ["10.0.11.0/24", "10.0.12.0/24", "10.0.13.0/24"]
cidr            = "10.0.8.0/21"
