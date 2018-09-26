package com.abc.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * The Class CaptchaServlet.
 */
public class Captcha {
public String captcha="";
	/** The Constant serialVersionUID. */

	/**
	 * Process request.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Captcha(HttpServletRequest request, HttpServletResponse response)throws IOException 
	{
		/*
		 * Define number characters contains the captcha image, declare global
		 */
		int iTotalChars = 6;

		/*
		 * Size image iHeight and iWidth, declare globl
		 */
		int iHeight = 40;
		int iWidth = 150;

		/*
		 * font style
		 */
		Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
		Font fntStyle2 = new Font("Verdana", Font.BOLD, 20);

		/*
		 * Possible random characters in the image
		 */
		Random randChars = new Random();
		String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);

		/*
		 * BufferedImage is used to create a create new image
		 */
		/*
		 * TYPE_INT_RGB - does not support transpatency, TYPE_INT_ARGB - support
		 * transpatency
		 */
		BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

		// Draw background rectangle and noisey filled round rectangles
		int iCircle = 15;
		// g2dImage.fillRect(0, 0, iWidth, iHeight);
		for (int i = 0; i < iCircle; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
			int iRadius = (int) (Math.random() * iHeight / 2.0);
			int iX = (int) (Math.random() * iWidth - iRadius);
			int iY = (int) (Math.random() * iHeight - iRadius);
			// g2dImage.fillRoundRect(iX, iY, iRadius * 2, iRadius * 2,100,100);
		}
		g2dImage.setFont(fntStyle1);
		for (int i = 0; i < iTotalChars; i++) {
			g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
			if (i % 2 == 0) {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
			} else {
				g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
			}
		}

		/*
		 * create jpeg image and display on the screen
		 */
		String path = request.getServletContext().getRealPath("");
		UUID uuid = UUID.randomUUID();
		captcha=uuid.toString();
		File outputfile = new File(path+"/captcha/"+captcha+".jpeg");
		ImageIO.write(biImage, "jpeg", outputfile);
		// osImage.close();

		/*
		 * Dispose function is used destory an image object
		 */
		g2dImage.dispose();
		HttpSession session = request.getSession();
		session.setAttribute("dns_security_code", sImageCode);
	}
}