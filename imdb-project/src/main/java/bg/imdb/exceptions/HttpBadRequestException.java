package bg.imdb.exceptions;

public class HttpBadRequestException extends RuntimeException {

  public HttpBadRequestException(String message) {
    super(message);
  }
}
