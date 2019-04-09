
matcher = content =~ "\\n(\\s\\n)+";
while(matcher.find()) {
  outputAS.add(matcher.start(), matcher.end(), 
               "PBreak", Factory.newFeatureMap());
               }
