resource "aws_route_table" "rt_public" {
  vpc_id = aws_vpc.vpc_learnlink.id

  tags = {
    Name = "rt_public"
  }

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw_vpc_learnlink.id
  }
}

resource "aws_route_table_association" "rt_sn_learnlink_public_1" {
  subnet_id      = aws_subnet.sn_learnlink_public_1.id
  route_table_id = aws_route_table.rt_public.id
}

resource "aws_route_table_association" "rt_sn_learnlink_public_2" {
  subnet_id      = aws_subnet.sn_learnlink_public_2.id
  route_table_id = aws_route_table.rt_public.id
}

resource "aws_route_table" "rt_private" {
  vpc_id = aws_vpc.vpc_learnlink.id

  tags = {
    Name = "rt_private"
  }

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.nat_learnlink.id
  }
}

resource "aws_route_table_association" "rt_sn_learnlink_private" {
  subnet_id      = aws_subnet.sn_learnlink_private.id
  route_table_id = aws_route_table.rt_private.id
}