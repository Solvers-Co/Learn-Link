resource "aws_internet_gateway" "igw_vpc_learnlink" {
  vpc_id = aws_vpc.vpc_learnlink.id

  tags = {
    Name = "igw_vpc_learnlink"
  }
}