resource "aws_nat_gateway" "nat_learnlink" {
  allocation_id = aws_eip.nat_eip.id
  subnet_id     = aws_subnet.sn_learnlink_public_1.id

  tags = {
    Name = "nat_learnlink"
  }
}

resource "aws_eip" "nat_eip" {
  vpc = true
}