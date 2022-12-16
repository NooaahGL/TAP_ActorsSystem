package Messages;

import ActorTypes.ActorProxyResponder;

public class GetInsultMessage extends Message{

	public GetInsultMessage() {
		
	}

	public GetInsultMessage(ActorProxyResponder actorProxyResponder) {
		super(actorProxyResponder);
	}

}
