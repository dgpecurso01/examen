# examen
Este proyecto es para poder correr un docker que tiene una calculadora bàsica de suma, resta, multiplicaciòn y divisiòn.

Para poder crear y correr el servicio VertX desde un docker, seguir las siguientes instrucciones:

1. Bajar docker
docker pull dgpecurso01/examen

2.Correr el docker
docker run -it -p 8088:8080 -e PBA=prueba dgpecurso01/examen java -jar sample-1.0-SNAPSHOT-fat.jar 

Nota. elegir el puerto de su preferencia

PARA BALANCEAR EL SERVICIO CON HAPROXY:

//instalar haproxy
sudo apt-get install haproxy
//dentro del documento descomentamos en .cfg y agregamos ENABLED=1
sudo nano /etc/default/haproxy
***************************
//dentro del documento
sudo nano etc/haproxy/haproxy.cfg
//escribimos hasta abajo
frontend www	
	bind localhost:9090
	default_backend site-backend	

	backend site-backend
		mode http
		balance roundrobin
		server lamp1 localhost:8081 check
		server lamp2 localhost:8082 check
		server lamp3 localhost:8083 check
		server lamp4 localhost:8084 check
		server lamp5 localhost:8085 check
		server lamp6 localhost:8086 check

********************************************************
docker run -d -p 8081:80 ngnix
docker run -d -p 8082:80 ngnix
docker run -d -p 8083:80 ngnix
docker run -d -p 8084:80 ngnix
docker run -d -p 8085:80 ngnix
docker run -d -p 8086:80 ngnix
