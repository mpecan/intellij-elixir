package org.elixir_lang.elixir_flex_lexer.named_sigil;

import org.elixir_lang.ElixirFlexLexer;
import org.elixir_lang.psi.ElixirTypes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * Created by luke.imhoff on 9/4/14.
 */
public class WordsTest {
    private ElixirFlexLexer flexLexer;

    private void reset(CharSequence charSequence) throws IOException {
        // start to trigger NAMED_SIGIL state
        CharSequence fullCharSequence = "~w" + charSequence;
        flexLexer.reset(fullCharSequence, 0, fullCharSequence.length(), ElixirFlexLexer.BODY);
        // consume '~'
        flexLexer.advance();
        // consume 'w'
        flexLexer.advance();
    }

    @Before
    public void setUp() {
        flexLexer = new ElixirFlexLexer((Reader) null);
    }

    @Test
    public void tripleDoubleQuotes() throws IOException {
        reset("\"\"\"");

        assertEquals(ElixirTypes.WORDS_HEREDOC_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP_HEREDOC_START, flexLexer.yystate());
    }

    @Test
    public void tripleSingleQuotes() throws IOException {
        reset("''''");

        assertEquals(ElixirTypes.WORDS_HEREDOC_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP_HEREDOC_START, flexLexer.yystate());
    }

    @Test
    public void openingBrace() throws IOException {
        reset("{");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }

    @Test
    public void openingBracket() throws IOException {
        reset("[");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }

    @Test
    public void openingChevron() throws IOException {
        reset("<");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }

    @Test
    public void openingDoubleQuotes() throws IOException {
        reset("\"");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }

    @Test
    public void openingParenthesis() throws IOException {
        reset("(");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }

    @Test
    public void singleQuote() throws IOException {
        reset("'");

        assertEquals(ElixirTypes.WORDS_PROMOTER, flexLexer.advance());
        assertEquals(ElixirFlexLexer.GROUP, flexLexer.yystate());
    }
}
