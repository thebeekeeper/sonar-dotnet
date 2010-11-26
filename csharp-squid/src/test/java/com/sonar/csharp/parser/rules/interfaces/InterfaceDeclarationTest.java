/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.parser.rules.interfaces;

import static com.sonar.sslr.test.parser.ParserMatchers.parse;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.parser.CSharpGrammar;
import com.sonar.csharp.parser.CSharpParser;

public class InterfaceDeclarationTest {

  CSharpParser p = new CSharpParser();
  CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.interfaceDeclaration);
    g.attributes.mock();
    g.typeParameterList.mock();
    g.interfaceBase.mock();
    g.typeParameterConstraintsClauses.mock();
    g.interfaceBody.mock();
  }

  @Test
  public void testOk() {
    assertThat(p, parse("interface id interfaceBody"));
    assertThat(p,
        parse("attributes new partial interface id typeParameterList interfaceBase typeParameterConstraintsClauses interfaceBody;"));
    assertThat(p, parse("public protected internal private interface id interfaceBody"));
  }

}
