
matcher = content =~ "\\n(\\s\\n)+\\s*";
while(matcher.find()) {
  outputAS.add(matcher.start(), matcher.end(), 
               "MajorBreak", Factory.newFeatureMap());
}

matcher = content =~ "\\n\\s+";
while(matcher.find()) {
  if (outputAS.getCovering("MajorBreak", matcher.start(), matcher.end()).isEmpty()) {
      outputAS.add(matcher.start(), matcher.end(), 
                   "MinorBreak", Factory.newFeatureMap());
  }
}


end = Utils.lengthLong(doc);
outputAS.add(end - 1L, end, "DocEnd", Factory.newFeatureMap());
