package io.github.drednote.telegram.session;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.generics.TelegramBot;

@AutoConfiguration
@EnableConfigurationProperties(SessionProperties.class)
public class SessionAutoConfiguration {

  @Bean(destroyMethod = "stop")
  @ConditionalOnProperty(
      prefix = "drednote.telegram.session",
      name = "type",
      havingValue = "LONG_POLLING",
      matchIfMissing = true
  )
  @ConditionalOnMissingBean
  @ConditionalOnSingleCandidate(TelegramBot.class)
  public TelegramBotSession longPollingTelegramBotSession(
      TelegramClient telegramClient, TelegramBot bot, SessionProperties properties
  ) {
    LongPollingSession session = new LongPollingSession(telegramClient, properties);
    session.setCallback(bot);
    session.start();
    return session;
  }

  @Bean(destroyMethod = "stop")
  @ConditionalOnProperty(
      prefix = "drednote.telegram.session",
      name = "type",
      havingValue = "WEBHOOKS"
  )
  @ConditionalOnMissingBean
  public TelegramBotSession webhooksTelegramBotSession() {
    throw new UnsupportedOperationException("Webhooks not implemented yet");
  }

  @Bean
  public CustomScopeConfigurer customScopeConfigurer() {
    CustomScopeConfigurer configurer = new CustomScopeConfigurer();
    configurer.addScope(BotSessionScope.BOT_SCOPE_NAME, new BotSessionScope());
    return configurer;
  }

  @Bean
  public TelegramClient telegramClient() {
    return new TelegramClientImpl(new RestTemplate());
  }

  @Bean
  public BotSessionContext botSessionContext() {
    return new BotSessionContext() {};
  }
}
