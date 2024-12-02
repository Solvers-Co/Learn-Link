# resource "tls_private_key" "learnlink_key" {
#   algorithm = "RSA"
#   rsa_bits  = 2048
# }

# resource "aws_key_pair" "learnlink_key_pair" {
#   key_name   = "learnlink-key-pair"
#   public_key = tls_private_key.learnlink_key.public_key_openssh
# }

# output "private_key_pem" {
#   value     = tls_private_key.learnlink_key.private_key_pem
#   sensitive = true
# }

# // Salvar chave .pem

# resource "local_file" "private_key" {
#   filename   = "learnlink-key-pair.pem"
#   content    = tls_private_key.learnlink_key.private_key_pem
#   file_permission = "0600"
# }