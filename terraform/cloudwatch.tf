resource "aws_sns_topic" "learnlink-sns" {
  name = "cloudwatch-alert-topic"
}

resource "aws_sns_topic_subscription" "email_subscriptions" {
  for_each = toset(["kauan.souza@sptech.school", "joao.batista@sptech.school", "sofhia.utaka@sptech.school", "mateus.capellari@sptech.school", "otavio.carvalho@sptech.school"])
  topic_arn = aws_sns_topic.learnlink-sns.arn
  protocol  = "email"
  endpoint  = each.value
}

resource "aws_cloudwatch_metric_alarm" "alarme_ec2_privada" {
  alarm_name          = "HighBackEndCPUAlarm"
  comparison_operator = "GreaterThanThreshold"
  evaluation_periods  = 2
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = 60
  statistic           = "Average"
  threshold           = 80.0
  alarm_actions       = [aws_sns_topic.learnlink-sns.arn]
  ok_actions          = [aws_sns_topic.learnlink-sns.arn]

  dimensions = {
    InstanceId = aws_instance.ec2-learnlink-backend.id 
  }
}

resource "aws_cloudwatch_metric_alarm" "alarme_ec2_publica1" {
  alarm_name          = "HighFrontEnd1CPUAlarm"
  comparison_operator = "GreaterThanThreshold"
  evaluation_periods  = 2
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = 60
  statistic           = "Average"
  threshold           = 80.0
  alarm_actions       = [aws_sns_topic.learnlink-sns.arn]
  ok_actions          = [aws_sns_topic.learnlink-sns.arn]

  dimensions = {
    InstanceId = aws_instance.ec2-learnlink-frontend-1.id 
  }
}

resource "aws_cloudwatch_metric_alarm" "alarme_ec2_publica2" {
  alarm_name          = "HighFrontEnd2CPUAlarm"
  comparison_operator = "GreaterThanThreshold"
  evaluation_periods  = 2
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = 60
  statistic           = "Average"
  threshold           = 80.0
  alarm_actions       = [aws_sns_topic.learnlink-sns.arn]
  ok_actions          = [aws_sns_topic.learnlink-sns.arn]

  dimensions = {
    InstanceId = aws_instance.ec2-learnlink-frontend-2.id 
  }
}