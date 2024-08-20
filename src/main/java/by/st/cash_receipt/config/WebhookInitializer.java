package by.st.cash_receipt.config;

import by.st.cash_receipt.telegram.TelegramBot;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class WebhookInitializer {
    private final TelegramBot telegramBot;

    public WebhookInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void init() {
        SetWebhook setWebhook = new SetWebhook();
        setWebhook.setUrl("https://warm-pianos-nail.loca.lt");
        telegramBot.setWebhook(setWebhook);
    }
}
