package vn.starshopvn.controller;

import java.io.File;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.starshopvn.ultis.Constant;

public class Upload {

	public String UploadFile(HttpServletRequest req, HttpServletResponse resp, String pid, String image) {
		String pname = req.getParameter(image);
		String iName = "";
		String upPath = Constant.upDir;
		File upDir = new File(upPath);
		if (!upDir.exists()) {
			upDir.mkdir();
		}
		try {
			Part p = req.getPart(image);
			if (p.getSize() > 0) {
				String name = Paths.get(p.getSubmittedFileName()).getFileName().toString();
				// Rename
				int index = name.lastIndexOf(".");
				String ext = name.substring(index + 1);
				String lowername = pname.replace(" ", "").toLowerCase();
				iName = pid + "_" + lowername + "." + ext;
				// Write file to directory
				p.write(upPath + "/" + iName);
				return iName;
			}
			else {
				return "default.jpg";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "default.jpg";
		}
	}
}
