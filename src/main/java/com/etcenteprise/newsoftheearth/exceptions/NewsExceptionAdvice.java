package com.etcenteprise.newsoftheearth.exceptions;

//@ControllerAdvice
//public class NewsExceptionAdvice{
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoResultException.class)
//    @ResponseBody ErrorInfo
//    handleDataNotFound(HttpServletRequest req, NoResultException ex) {
//        return new ErrorInfo(req.getRequestURL(), ex);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(NullPointerException.class)
//    @ResponseBody ErrorInfo
//    handleNullData(HttpServletRequest req, NullPointerException ex) {
//        return new ErrorInfo(req.getRequestURL(), ex);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseBody ErrorInfo
//    handleNotFoundData(HttpServletRequest req, NotFoundException ex) {
//        return new ErrorInfo(req.getRequestURL(), ex);
//    }
//
//}


//    @ExceptionHandler(NoResultException.class)
//    public String findException(NoResultException ex) {
//        return "error";
//    }



//    @ExceptionHandler({NoResultException.class,NullPointerException.class})
//    public String databaseError(NoResultException ex) {
//
//        String x  = ex.getMessage();
//        // Nothing to do.  Returns the logical view name of an error page, passed
//        // to the view-resolver(s) in usual way.
//        // Note that the exception is NOT available to this view (it is not added
//        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//        // below.
//        return x;
//    }


//    @ExceptionHandler(NoResultException.class)
//    public ResponseEntity<NewsErrors> findException(NoResultException ex) {
//        NewsErrors error = new NewsErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
//        return new ResponseEntity<NewsErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }



//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(
//            EntityNotFoundException ex) {
//        NewsErrors apiError = new NewsErrors(HttpStatus.MULTI_STATUS);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }
//
//    private ResponseEntity<Object> buildResponseEntity(NewsErrors apiError) {
//        return  new ResponseEntity<>( apiError,HttpStatus.OK);
//    }