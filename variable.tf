# Define variables
variable "vm_count" {
  description = "Number of VMs to create"
  type        = number
}

variable "instance_type" {
  description = "EC2 instance type"
  type        = string
}

variable "ami" {
  description = "AMI ID for instances"
  type        = string
}

variable "env" {
  description = "environment"
  type        = string
}

variable "private_subnets" {
  description = "CIDR blocks per le subnet private"
  type        = list(string)
}

variable "public_subnets" {
  description = "CIDR blocks per le subnet pubbliche"
  type        = list(string)
}

variable "cidr" {
  description = "CIDR for the VPC"
  type        = string
}