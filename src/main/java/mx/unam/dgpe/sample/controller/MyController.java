package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    public static String pba;

    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/primero").handler(this::primero);
        router.get("/api/sumar").handler(this::sumar);
        router.get("/api/restar").handler(this::restar);
        router.get("/api/multiplicar").handler(this::multiplicar);
        router.get("/api/dividir").handler(this::dividir);
                
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        pba = System.getenv("PBA");
        logger.info("Vertical iniciada !!!");
    }  
    private void primero(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String mode = request.getParam("mode");
        String jsonResponse = procesa(mode);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void sumar(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int A = Integer.parseInt(request.getParam("A"));
        int B = Integer.parseInt(request.getParam("B"));
        int C = A + B;
        String operacion = "SUMAR";
        String jsonResponse = procesa(operacion,A,B,C);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void restar(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int A = Integer.parseInt(request.getParam("A"));
        int B = Integer.parseInt(request.getParam("B"));
        int C = A - B;
        String operacion = "RESTAR";
        String jsonResponse = procesa(operacion,A,B,C);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void multiplicar(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int A = Integer.parseInt(request.getParam("A"));
        int B = Integer.parseInt(request.getParam("B"));
        int C = A * B;
        String operacion = "MULTIPLICAR";
        String jsonResponse = procesa(operacion,A,B,C);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void dividir(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        float A = Integer.parseInt(request.getParam("A"));
        float B = Integer.parseInt(request.getParam("B"));
        float C = A / B;
        String operacion = "DIVIDIR";
        String jsonResponse = procesafloat(operacion,A,B,C);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private String procesa(String operacion, int A, int B, int C) {
        Map<Object, Object> info = new HashMap<>();
        info.put("servidor", pba);
        info.put("operacion", operacion);
        info.put("A", A);
        info.put("B", B);
        info.put("resultado", C);
        return Json.encodePrettily(info);
    }
    
    private String procesafloat(String operacion, float A, float B, float C) {
        Map<Object, Object> info = new HashMap<>();
        info.put("servidor", pba);
        info.put("operacion", operacion);
        info.put("A", A);
        info.put("B", B);
        info.put("resultado", C);
        return Json.encodePrettily(info);
    }

    private String procesa(String decoded) {
        Map<String, String> autos = new HashMap<>();
        autos.put("primero", "Ferrari");
        autos.put("segundo", "Lamborgini");
        autos.put("tercero", "Bugatti");
        
        Map<Object, Object> info = new HashMap<>();
        info.put("decoded", decoded);
        info.put("nombre", "gustavo");
        info.put("edad", "21");
        info.put("autos", autos);
	info.put("variable", pba);
        return Json.encodePrettily(info);
    }

    public String construye(String fuente) {
        String tmp = fuente + fuente;
        return String.valueOf(tmp.substring(1, tmp.length()-1).contains(fuente)); 
    }

}
