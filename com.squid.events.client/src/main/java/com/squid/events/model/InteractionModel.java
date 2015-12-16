package com.squid.events.model;

import java.util.HashMap;

/**
 * Properties to track retrieval events. It extends from the Account model.
 * @author sergefantino
 *
 */
public class InteractionModel extends AccountModel {

    /**
     *
     */
    private static final long serialVersionUID = 7602749874218394598L;

    public static final String interactionSchema = "interaction:pub_1.0";

    public static final String interactionEventType = "interaction";

    /**
     * Content object id on which the user interact
     */
    public static final String contentId = "it:contentId";

    /**
     * Content type the page handle (ex article, reference, ...)
     */
    public static final String contentType = "it:contentType";

    /**
     * Action done on the content (display/creation/deletion/modification/add/remove/validate/...)
     */
    public static final String actionType = "it:actionType";

    /**
     * Part id of the content on which the user interact (optional)
     */
    public static final String componentId = "it:componentId";

    /**
     * Part type of the content on which the user interact (optional)
     */
    public static final String componentType = "it:componentType";

    /**
     * Additional key-value map for better description of the interaction
     */
    public static final String interactionContext = "it:interactionContext";

    protected InteractionModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    public InteractionModel(String eventType) {
        super(interactionSchema,eventType);
    }

    public InteractionModel withContentId(String contentId) {
        super.put(InteractionModel.contentId, contentId);
        return this;
    }

    public InteractionModel withContentType(String contentType) {
        super.put(InteractionModel.contentType, contentType);
        return this;
    }

    public InteractionModel withActionType(String actionType) {
        super.put(InteractionModel.actionType, actionType);
        return this;
    }

    public InteractionModel withComponentId(String componentId) {
        super.put(InteractionModel.componentId, componentId);
        return this;
    }

    public InteractionModel withComponentType(String componentType) {
        super.put(InteractionModel.componentType, componentType);
        return this;
    }

    public InteractionModel withInteractionContext(HashMap<String, Object> interactionContext) {
        super.put(InteractionModel.interactionContext, interactionContext);
        return this;
    }
}