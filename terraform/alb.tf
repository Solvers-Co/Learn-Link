# Cria o ALB
resource "aws_lb" "alb-learnlink-web" {
  name               = "alb-learnlink-web"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.sg_learnlink_frontend.id]
  subnets            = [aws_subnet.sn_learnlink_public_1.id, aws_subnet.sn_learnlink_public_2.id]

  enable_deletion_protection = false
}

# Target Group
resource "aws_lb_target_group" "tg-learnlink" {
  name     = "tg-learnlink"
  port     = 80
  protocol = "HTTP"
  vpc_id     = aws_vpc.vpc_learnlink.id

  health_check {
    path                = "/"
    protocol            = "HTTP"
    interval            = 30
    timeout             = 5
    healthy_threshold   = 2
    unhealthy_threshold = 2
  }
}

# Listener HTTP (80)
resource "aws_lb_listener" "listener_http" {
  load_balancer_arn = aws_lb.alb-learnlink-web.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.tg-learnlink.arn
  }
}

# # Listener HTTPS (443) - opcional, requer certificado SSL
# resource "aws_lb_listener" "listener_https" {
#   load_balancer_arn = aws_lb.alb-learnlink-web.arn
#   port              = 443
#   protocol          = "HTTPS"
#   ssl_policy        = "ELBSecurityPolicy-2016-08"  # Política de segurança

#   certificate_arn = "arn:aws:acm:us-east-1:123456789012:certificate/xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxx"  # Substitua pelo ARN do certificado SSL

#   default_action {
#     type             = "forward"
#     target_group_arn = aws_lb_target_group.tg_learnlink.arn
#   }
# }

# Associação de instância ao Target Group
resource "aws_lb_target_group_attachment" "tg_attachment_public_1" {
  target_group_arn = aws_lb_target_group.tg-learnlink.arn
  target_id        = aws_instance.ec2-learnlink-frontend-1.id
  port             = 80
}

resource "aws_lb_target_group_attachment" "tg_attachment_public_2" {
  target_group_arn = aws_lb_target_group.tg-learnlink.arn
  target_id        = aws_instance.ec2-learnlink-frontend-2.id
  port             = 80
}