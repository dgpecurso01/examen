#!/bin/sh
curl http://localhost:9090/api/primero?mode=abc
curl "http://localhost:9090/api/sumar?A=34&B=23"
curl "http://localhost:9090/api/sumar?A=23&B=2"
curl "http://localhost:9090/api/restar?A=8&B=2"
curl "http://localhost:9090/api/restar?A=100&B=90"
curl "http://localhost:9090/api/restar?A=5&B=2"
curl "http://localhost:9090/api/restar?A=15&B=4"
curl "http://localhost:9090/api/multiplicar?A=7&B=7"
curl "http://localhost:9090/api/multiplicar?A=10&B=20"
curl "http://localhost:9090/api/multiplicar?A=8&B=9"
curl "http://localhost:9090/api/dividir?A=1&B=2"
curl "http://localhost:9090/api/dividir?A=2&B=1"
curl "http://localhost:9090/api/dividir?A=9&B=3"
curl "http://localhost:9090/api/dividir?A=100&B=10"

