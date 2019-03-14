package gate.creole.sentiment;

import gate.creole.PackagedController;
import gate.creole.metadata.AutoInstance;
import gate.creole.metadata.AutoInstanceParam;
import gate.creole.metadata.CreoleResource;

@CreoleResource(name = "UN General Assembly resolution analysis",
    comment = "Information extraction from UN General Assembly resolutions",
    autoinstances = @AutoInstance(parameters = {
	@AutoInstanceParam(name="pipelineURL", value="resources/application.xgapp"),
	@AutoInstanceParam(name="menu", value="UN GA IE")}))
public class SentimentAnalysis extends PackagedController {

    //private static final long serialVersionUID = 5965895217733412732L;

}
