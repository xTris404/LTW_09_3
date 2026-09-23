package vn.iotstar.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = "Đã xảy ra lỗi.";
        if (status != null) {
            int code = Integer.parseInt(status.toString());
            if (code == 404) message = "Không tìm thấy trang (404).";
            else if (code == 403) message = "Bạn không có quyền truy cập (403).";
            else if (code == 500) message = "Lỗi máy chủ (500).";
        }
        model.addAttribute("message", message);
        return "error";
    }
}
