/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pmpc.captcha.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.SimpleCaptchaServlet;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

/**
 *
 * @author marcusvgl
 */
public class CustomCaptchaServlet extends SimpleCaptchaServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Color> listaCores = Arrays.asList(Color.LIGHT_GRAY);
        List<Font> listaFontes = Arrays.asList(new Font("Arial", Font.BOLD, 80), 
                                               new Font("Courier", Font.BOLD, 80),
                                               new Font("Time New Roman", Font.BOLD, 80));

        Captcha captcha = new Captcha.Builder(300, 100)
                .addText(new DefaultTextProducer(), 
                             new DefaultWordRenderer(listaCores, 
                                                     listaFontes))
                .addBackground(new GradiatedBackgroundProducer())
                .gimp()
                .gimp(new DropShadowGimpyRenderer())
                .gimp(new FishEyeGimpyRenderer())
                .addNoise(new StraightLineNoiseProducer())
                .addNoise()
                .addNoise()
                .addNoise()
                .addBorder()
                .build();

        CaptchaServletUtil.writeImage(resp, captcha.getImage());
        req.getSession()
           .setAttribute(Captcha.NAME, captcha);
    }// Fim do metodo doGet

}// Fim da classe CustomCaptchaServlet
