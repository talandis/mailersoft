#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Sometimes you want to write a Python script for a project written in PHP.
# For the most part, this is easy, but for a few key things, PHP breaks with
# the standard and does things in a its own way.  For these cases, you can use
# this module to compensate.
#

def http_build_query(params, convention="%s"):
  """

    This was ripped shamelessly from a PHP forum and ported to Python:

      http://www.codingforums.com/showthread.php?t=72179

    Essentially, it's a (hopefully perfect) replica of PHP's
    http_build_query() that allows you to pass multi-dimensional arrays
    to a URL via POST or GET.

  """

  from urllib import quote

  if len(params) == 0:
    return ""
  else:
    output = ""
    for key in params.keys():

      if type(params[key]) is dict:
        output = output + http_build_query(params[key], convention % (key) + "[%s]")

      elif type(params[key]) is list:

        i = 0
        newparams = {}
        for element in params[key]:
          newparams[str(i)] = element
          i = i + 1

        output = output + http_build_query(newparams, convention % (key) + "[%s]")

      else:
        key = quote(key)
        val = quote(str(params[key]))
        output = output + convention % (key) + "=" + val + "&"

  return output
