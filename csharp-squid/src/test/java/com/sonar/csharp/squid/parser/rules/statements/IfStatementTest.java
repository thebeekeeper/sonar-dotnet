/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.squid.parser.rules.statements;

import com.sonar.csharp.squid.CSharpConfiguration;
import com.sonar.csharp.squid.api.CSharpGrammar;
import com.sonar.csharp.squid.parser.CSharpParser;
import com.sonar.sslr.impl.Parser;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

import static com.sonar.sslr.test.parser.ParserMatchers.*;
import static org.junit.Assert.*;

public class IfStatementTest {

  private final Parser<CSharpGrammar> p = CSharpParser.create(new CSharpConfiguration(Charset.forName("UTF-8")));
  private final CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.ifStatement);
  }

  @Test
  public void testOk() {
    g.expression.mock();
    g.embeddedStatement.mock();
    assertThat(p, parse("if ( expression ) embeddedStatement"));
    assertThat(p, parse("if ( expression ) embeddedStatement else embeddedStatement"));
  }

  @Test
  public void testRealLife() throws Exception {
    assertThat(
        p,
        parse("if (true) { loggingEvent.GetProperties()[\"log4jmachinename\"] = loggingEvent.LookupProperty(LoggingEvent.HostNameProperty); }"));
  }

}
