
AnnotationSet paragraphs = doc.getAnnotations("Original markups").get("p");
AnnotationSet spaces = inputAS.get("SpaceToken");

for (Annotation space : spaces) {
    if (Utils.getCoveringAnnotations(paragraphs, space).isEmpty()) {
        Utils.addAnn(outputAS, space, "PBreak", Factory.newFeatureMap());
    }
}
