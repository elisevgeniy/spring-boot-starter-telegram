package com.github.drednote.telegram.updatehandler.scenario;

import com.github.drednote.telegram.core.ActionExecutor;
import com.github.drednote.telegram.core.request.BotRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
public class StepImpl implements Step {

  @NonNull
  final Scenario root;
  @NonNull
  final ActionExecutor actionExecutor;
  @NonNull
  final String name;

  @Override
  public Object onAction(BotRequest request) throws Exception {
    return actionExecutor.onAction(request);
  }

  @NonNull
  @Override
  public String getName() {
    return name;
  }

  @NonNull
  @Override
  public Scenario getRoot() {
    return root;
  }

  @Override
  public String toString() {
    return "'%s'".formatted(name);
  }
}
