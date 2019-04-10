import gate.creole.ANNIEConstants
import gate.mimir.SemanticAnnotationHelper.Mode
import gate.mimir.index.OriginalMarkupMetadataHelper
import gate.mimir.db.DBSemanticAnnotationHelper as DefaultHelper

tokenASName = ""
tokenAnnotationType = ANNIEConstants.TOKEN_ANNOTATION_TYPE
tokenFeatures = {
  string()
  category()
  root()
}

semanticASName = ""
semanticAnnotations = {
  index {
    annotation helper:new DefaultHelper(annType:'Sentence')
  }
  index {
    annotation helper:new DefaultHelper(annType:'Person', nominalFeatures:["gender"])
    annotation helper:new DefaultHelper(annType:'Location', nominalFeatures:["locType"])
    annotation helper:new DefaultHelper(annType:'Organization', nominalFeatures:["orgType"])
    annotation helper:new DefaultHelper(annType:'Date', integerFeatures:["normalized"])
    annotation helper:new DefaultHelper(annType:'Document', integerFeatures:["date"], mode:Mode.DOCUMENT)
  }
}
documentRenderer = new OriginalMarkupMetadataHelper()
documentMetadataHelpers = [documentRenderer]

// miscellaneous options - these are the defaults
//timeBetweenBatches = 1.hour
//maximumBatches = 20
