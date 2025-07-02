vm_count      = 2
instance_type = "t3.micro"
ami           = "ami-0929684ff3ecf3f6d"
env           = "Dev"
private_subnets = ["10.0.0.0/24", "10.0.1.0/24", "10.0.2.0/24"]
public_subnets  = ["10.0.3.0/24", "10.0.4.0/24", "10.0.5.0/24"]
cidr            = "10.0.0.0/21"