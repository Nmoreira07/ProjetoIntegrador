package br.com.carstore.servlet;

import br.com.carstore.dao.CarDao;
import br.com.carstore.model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-car")
public class Createcarservlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String carName = req.getParameter("car-name");
        String carcolor = req.getParameter("car-color");

        System.out.println(carName);
        System.out.println(carcolor);
        req.getRequestDispatcher("index.html").forward(req, resp);

        Car car = new Car();
        CarDao cardao = new CarDao();

        car.setName(carName);
        car.setColor(carcolor);

        cardao.createCar(car);
    }
}
