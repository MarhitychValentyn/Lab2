package com.dev.bank.services;

import com.dev.bank.logger.AppLogger;
import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import com.dev.bank.services.client.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        AuthLoginResponse response = new AuthLoginResponse();
        AppLogger.log("Спроба авторизації користувача: " + request.getUsername());

        final String username = request.getUsername();
        if (username == null || username.trim().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Username є обов'язковим полем.");
            AppLogger.error("Помилка авторизації: відсутній username.");
            return response;
        }

        final String password = request.getPassword();
        if (password == null || password.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Password є обов'язковим полем.");
            AppLogger.error("Помилка авторизації: відсутній password.");
            return response;
        }

        if (password.length() < 5) {
            response.setSuccess(false);
            response.setMessage("Введений пароль занадто короткий.");
            AppLogger.error("Помилка авторизації: пароль для " + username + " занадто короткий.");
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Користувач успішно авторизований.");
        response.setToken("2132132132");
        AppLogger.log("Успішна авторизація користувача: " + username);

        return response;
    }



    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        AuthRegisterResponse response = new AuthRegisterResponse();
        AppLogger.log("Спроба реєстрації нового користувача: " + request.getUsername());

        final String username = request.getUsername();
        if (username == null || username.trim().isEmpty()) {
            return validationError(response, "Username є обов'язковим полем.");
        }

        if (username.length() < 3) {
            return validationError(response, "Username повинен містити мінімум 3 символи.");
        }

        final String password = request.getPassword();
        if (password == null || password.isEmpty()) {
            return validationError(response, "Password є обов'язковим полем.");
        }
        if (password.length() < 8) {
            return validationError(response, "Password повинен містити мінімум 8 символів.");
        }

        final String email = request.getEmail();
        if (email == null || email.trim().isEmpty()) {
            return validationError(response, "Email є обов'язковим полем.");
        }
        if (!email.contains("@") || email.indexOf('@') != email.lastIndexOf('@') || email.indexOf('.') < email.indexOf('@')) {
            return validationError(response, "Email має невірний формат. Перевірте наявність '@' та '.'.");
        }

        final String birthday = request.getBirthday();
        if (birthday == null || birthday.trim().isEmpty()) {
            return validationError(response, "Birthday є обов'язковим полем.");
        }
        // Проста перевірка: перевіряємо, що рядок містить роздільники '-' в потрібних місцях (DD-MM-YYYY)
        if (birthday.length() != 10 || birthday.charAt(2) != '-' || birthday.charAt(5) != '-') {
            return validationError(response, "Birthday має невірний формат. Очікується DD-MM-YYYY.");
        }

        final String phoneNumber = request.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            if (phoneNumber.length() != 10) {
                return validationError(response, "Номер телефону має невірний формат.");
            }
        }

        response.setSuccess(true);
        response.setMessage("Користувач успішно зареєстрований.");
        AppLogger.log("Успішна реєстрація нового користувача: " + request.getUsername());

        return response;
    }

    /**
     * Допоміжний метод для формування відповіді про помилку валідації та логування.
     */
    private AuthRegisterResponse validationError(AuthRegisterResponse response, String message) {
        response.setSuccess(false);
        response.setMessage(message);
        AppLogger.error("Помилка валідації реєстрації: " + message);
        return response;
    }
}



