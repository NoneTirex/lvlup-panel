package pl.edu.tirex.lvlup.panel;

import org.json.JSONException;
import org.junit.Test;
import pl.edu.tirex.lvlup.api.models.auth.LvlupToken;
import pl.edu.tirex.lvlup.api.requests.TokenRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        TokenRequest tokenRequest = new TokenRequest();
        try
        {
            LvlupToken lvlupToken = tokenRequest.readToken("{\"token\":\"siema\"}");
            assertFalse(lvlupToken.toString(), false);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            assertFalse(e.getMessage(), true);
        }
    }
}