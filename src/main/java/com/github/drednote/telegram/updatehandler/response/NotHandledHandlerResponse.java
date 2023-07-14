package com.github.drednote.telegram.updatehandler.response;

import com.github.drednote.telegram.core.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
public class NotHandledHandlerResponse extends AbstractHandlerResponse {

  @Override
  public void process(UpdateRequest updateRequest) throws TelegramApiException {
    String text = "Неизвестная команда или текст, попробуйте что нибудь другое";
    sendString(text, updateRequest);
  }
}
