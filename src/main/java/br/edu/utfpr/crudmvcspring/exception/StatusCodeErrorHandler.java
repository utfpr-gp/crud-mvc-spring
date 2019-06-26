package br.edu.utfpr.crudmvcspring.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Em contrução! Não funciona ainda.
 */
@Controller
@RequestMapping("/errors")
public class StatusCodeErrorHandler {


    @GetMapping
    public ModelAndView renderErrorPage(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("error/error-handler");
//        String errorMsg = "";
//        int httpErrorCode = getErrorCode(request);
//
//        switch (httpErrorCode) {
//            case 400: {
//                errorMsg = "Http Error Code: 400. Bad Request";
//                break;
//            }
//            case 401: {
//                errorMsg = "Http Error Code: 401. Unauthorized";
//                break;
//            }
//            case 404: {
//                errorMsg = "Http Error Code: 404. Resource not found";
//                break;
//            }
//            case 500: {
//                errorMsg = "Http Error Code: 500. Internal Server Error";
//                break;
//            }
//        }
//        mv.addObject("message", errorMsg);
//        mv.addObject("url", request.getRequestURL());
        return mv;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
