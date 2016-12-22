// HTMLParser Library - A java-based parser for HTML
// http://htmlparser.org
// Copyright (C) 2006 Claude Duguay
//
// Revision Control Information
//
// $URL: file:///svn/p/htmlparser/code/trunk/lexer/src/main/java/org/htmlparser/util/ParserException.java $
// $Author: derrickoswald $
// $Date: 2011-04-25 09:39:12 +0000 (Mon, 25 Apr 2011) $
// $Revision: 74 $
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the Common Public License; either
// version 1.0 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// Common Public License for more details.
//
// You should have received a copy of the Common Public License
// along with this library; if not, the license is available from
// the Open Source Initiative (OSI) website:
//   http://opensource.org/licenses/cpl1.0.php

package org.htmlparser.util;

/**
 * Library-specific support for chained exceptions.
 *
 * @see ChainedException
 **/
public class ParserException
  extends ChainedException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ParserException() {}

  public ParserException(String message)
  {
    super(message);
  }

  public ParserException(Throwable throwable)
  {
    super(throwable);
  }

  public ParserException(String message, Throwable throwable)
  {
    super(message, throwable);
  }
}

