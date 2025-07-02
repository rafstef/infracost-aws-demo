vm_count      = 5
instance_type = "t3.micro"
ami           = "ami-0929684ff3ecf3f6d"
env           = "Prod"
private_subnets = ["10.0.16.0/24", "10.0.17.0/24", "10.0.18.0/24"]
public_subnets  = ["10.0.19.0/24", "10.0.20.0/24", "10.0.21.0/24"]
cidr            = "10.0.16.0/21"