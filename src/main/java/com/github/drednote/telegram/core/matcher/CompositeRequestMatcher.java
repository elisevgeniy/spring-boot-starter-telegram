package com.github.drednote.telegram.core.matcher;

import com.github.drednote.telegram.core.request.BotRequest;
import java.util.Collection;
import java.util.List;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

public class CompositeRequestMatcher implements RequestMatcher {

  private final List<RequestMatcher> matchers;

  public CompositeRequestMatcher(Collection<RequestMatcher> matchers) {
    this.matchers = matchers.stream().sorted(AnnotationAwareOrderComparator.INSTANCE).toList();
  }

  @Override
  public boolean matches(BotRequest request) {
    return matchers.stream().anyMatch(requestMatcher -> requestMatcher.matches(request));
  }
}
