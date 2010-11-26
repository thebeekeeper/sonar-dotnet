/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.parser.rules.delegates;

import static com.sonar.sslr.test.parser.ParserMatchers.parse;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.parser.CSharpGrammar;
import com.sonar.csharp.parser.CSharpParser;

public class DelegateDeclarationTest {

  CSharpParser p = new CSharpParser();
  CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.delegateDeclaration);
    g.attributes.mock();
    g.returnType.mock();
    g.typeParameterList.mock();
    g.formalParameterList.mock();
    g.typeParameterConstraintsClauses.mock();
  }

  @Test
  public void testOk() {
    assertThat(p, parse("delegate returnType id();"));
    assertThat(p, parse("attributes new delegate returnType id typeParameterList (formalParameterList) typeParameterConstraintsClauses;"));
    assertThat(p, parse("public protected internal private delegate returnType id();"));
  }

}
