resource "aws_instance" "ec2-learnlink-frontend-1" {
  ami           = "ami-0e86e20dae9224db8"
  instance_type = "t2.micro"
  tags = {
    Name = "ec2-learnlink-frontend-1"
  }

  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 30
    volume_type = "gp3"
  }

  security_groups = [aws_security_group.sg_learnlink_frontend.id]
  # key_name        = aws_key_pair.learnlink_key_pair.key_name
  key_name = "learnlink-fixo"
  subnet_id       = aws_subnet.sn_learnlink_public_1.id
}

resource "aws_instance" "ec2-learnlink-frontend-2" {
  ami           = "ami-0e86e20dae9224db8"
  instance_type = "t2.micro"
  tags = {
    Name = "ec2-learnlink-frontend-2"
  }

  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 30
    volume_type = "gp3"
  }

  security_groups = [aws_security_group.sg_learnlink_frontend.id]
  # key_name        = aws_key_pair.learnlink_key_pair.key_name
  key_name = "learnlink-fixo"
  subnet_id       = aws_subnet.sn_learnlink_public_2.id
}

resource "aws_instance" "ec2-learnlink-backend" {
  ami           = "ami-0e86e20dae9224db8"
  instance_type = "t2.micro"
  tags = {
    Name = "ec2-learnlink-backend"
  }

  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 30
    volume_type = "gp3"
  }

  security_groups = [aws_security_group.sg_learnlink_backend.id]
  # key_name        = aws_key_pair.learnlink_key_pair.key_name
  key_name = "learnlink-fixo"
  subnet_id       = aws_subnet.sn_learnlink_private.id
}