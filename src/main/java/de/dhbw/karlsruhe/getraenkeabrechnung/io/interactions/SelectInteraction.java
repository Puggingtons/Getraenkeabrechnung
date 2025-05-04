package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

import java.util.HashMap;
import java.util.Map;

public class SelectInteraction extends Interaction<String>
{

    private final Map<String, String> options;
    private final StringInput input;

    public SelectInteraction()
    {
        this(new HashMap<>());
    }

    public SelectInteraction(Map<String, String> options)
    {
        this(options, DEFAULT_PROMPT);
    }

    public SelectInteraction(Map<String, String> options, String prompt)
    {
        this.options = options;

        input = new StringInput(prompt);
    }

    // todo: move this functionality to a new class
    public void pushOption(String option)
    {
        this.options.put(String.valueOf(this.options.size()), option);
    }

    public void addOption(String key, String option)
    {
        this.options.put(key, option);
    }

    String usage()
    {
        StringBuilder sb = new StringBuilder();
        options.forEach((k, v) -> sb.append("[ ").append(k).append(" ] ").append(v).append("\n"));

        return sb.toString();
    }

    protected void execute()
    {
        Result<String> result = input.prompt();

        if (result.isNone())
        {
            System.out.println("Invalid input!");
            failure();
            return;
        }

        if (result.isHelp())
        {
            explain();
            return;
        }

        String key = result.getValue();

        if (!options.containsKey(key))
        {
            System.out.println("Unknown option: " + key);
            return;
        }

        success(key);
    }

    protected void clearOptions()
    {
        this.options.clear();
    }
}