resource "aws_route_table" "rt_vpc_learnlink" {
  vpc_id = aws_vpc.vpc_learnlink.id

  tags = {
    Name = "rt_vpc_learnlink"
  }
}


// Criando uma rota para a internet gateway "igw_vpc_learnlink" na route table "rt_vpc_learnlink"
// A rota é para qualquer destino (0.0.0.0/0)

resource "aws_route" "route" {
  route_table_id         = aws_route_table.rt_vpc_learnlink.id
  gateway_id             = aws_internet_gateway.igw_vpc_learnlink.id
  destination_cidr_block = "0.0.0.0/0"
}


// Associando a route table "rt_vpc_learnlink" com a sub-rede "sn_learnlink_public"

resource "aws_route_table_association" "rt_sn_learnlink_public_1" {
  subnet_id      = aws_subnet.sn_learnlink_public_1.id
  route_table_id = aws_route_table.rt_vpc_learnlink.id
}

resource "aws_route_table_association" "rt_sn_learnlink_public_2" {
  subnet_id      = aws_subnet.sn_learnlink_public_2.id
  route_table_id = aws_route_table.rt_vpc_learnlink.id
}


// Associando a route table "rt_vpc_learnlink" com a sub-rede "sn_learnlink_private"

resource "aws_route_table_association" "rt_sn_learnlink_private" {
  subnet_id      = aws_subnet.sn_learnlink_private.id
  route_table_id = aws_route_table.rt_vpc_learnlink.id
}
