package com.example.taskapi.validation;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Simple validator. IN PRODUCTION you should use a stricter whitelist & sandboxing approach.
 */
public class CommandValidator {
    // Allowed top-level commands (very strict whitelist)
    private static final Set<String> WHITELIST = Set.of(
        "echo","date","ls","cat","uname","whoami","uptime","pwd","printf","sleep"
    );

    // Forbid dangerous metacharacters and keywords
    private static final Pattern DISALLOWED = Pattern.compile("[;&|`$<>\\\\]");
    private static final Pattern WORD_CHARS = Pattern.compile("^[A-Za-z0-9_./\\-\\s\"']+$");

    public static boolean isSafe(String command) {
        if (command == null || command.isBlank()) return false;
        if (DISALLOWED.matcher(command).find()) return false; // blocks ; | & ` $ < > \ and similar
        if (!WORD_CHARS.matcher(command).matches()) return false;

        // check first token is whitelisted
        String first = command.trim().split("\\s+")[0];
        return WHITELIST.contains(first);
    }
}
