# resource "aws_eip" "ip_publico_learnlink" {
#   vpc = true
# }

# resource "aws_eip_association" "example" {
#   instance_id   = aws_instance.example.id
#   allocation_id = aws_eip.ip_publico_learnlink.id
# }