package vn.starshopvn.ultis;

public class Constant {
    // Đường dẫn thực tế trên máy chủ
    public static final String upDir = "D:\\JavaProjects\\starshopvn-haianh\\starshopvn-haianh\\image";
    
    // Hàm trả về đường dẫn thực tế
    public static final String upDIR(String type) {
        return upDir + "\\" + type;
    }

    // Đường dẫn URL cho trình duyệt
    public static final String UPLOAD_DIR = "/image"; 
    public static final String FILE_UPLOAD_PATH = "image/"; 
}
