package lt.tyz.evolut.utils;

import lt.tyz.evolut.presentation.RepresentationOut;
import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class MyExceptions {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RepresentationOut> handleException(HttpServletRequest request, Exception e) {
        return constructResponse(request, e);
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<RepresentationOut> handleSQLIntegrityConstraintViolationException(HttpServletRequest request, SQLIntegrityConstraintViolationException e) {
        return constructResponse(request, e);
    }

    // reikalingas?
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<RepresentationOut> handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e) {
        return constructResponse(request, e);
    }

    // jei pvz /customer/addZ
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RepresentationOut> handleNotFoundResourceException(HttpServletRequest request, NoHandlerFoundException e) {
        return constructResponse(request, e);
    }

    private ResponseEntity<RepresentationOut> constructResponse(HttpServletRequest request, Exception e) {
        JSONArray messages = new JSONArray();
        messages.add(e.getCause().getMessage());
        messages.add(request.getRequestURI());
        return ResponseEntity.ok(new RepresentationOut(null, false, HttpStatus.BAD_REQUEST, messages));
    }
}

