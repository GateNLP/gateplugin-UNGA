
end = Utils.lengthLong(doc);

if (end > 0L) {
    outputAS.add(end - 1L, end, "DocEnd", Factory.newFeatureMap());

    matcher = content =~ "\\n(\\s\\n)+\\s*";
    while(matcher.find()) {
      if ( outputAS.getCovering("DocEnd", matcher.start(), matcher.end()).isEmpty() &&
           outputAS.get("DocEnd", matcher.start(), matcher.end()).isEmpty() ) {
          outputAS.add(matcher.start(), matcher.end(), 
                   "MajorBreak", Factory.newFeatureMap());
          outputAS.add(matcher.start(), matcher.end(), 
                   "Split", Utils.featureMap("source", "MajorBreak", "kind", "external"));
      }
    }

    matcher = content =~ "\\n\\s+";
    while(matcher.find()) {
      if (outputAS.getCovering("MajorBreak", matcher.start(), matcher.end()).isEmpty()) {
          outputAS.add(matcher.start(), matcher.end(), 
                       "MinorBreak", Factory.newFeatureMap());
          outputAS.add(matcher.start(), matcher.end(), 
                   "Split", Utils.featureMap("source", "MinorBreak", "kind", "external"));
      }
    }
}
