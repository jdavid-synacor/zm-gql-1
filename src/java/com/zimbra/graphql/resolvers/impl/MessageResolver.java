/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2018 Synacor, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.graphql.resolvers.impl;

import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.impl.ZXMLMessageRepository;
import com.zimbra.soap.mail.type.Msg;
import com.zimbra.soap.mail.type.MsgSpec;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The MessageResolver class.<br>
 * Contains message schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class MessageResolver {

    protected ZXMLMessageRepository messageRepository = null;

    /**
     * Creates an instance with specified message repository.
     *
     * @param messageRepository The message repository
     */
    public MessageResolver(ZXMLMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GraphQLQuery(description = "Retrieve a message by given properties.")
    public Msg message(
        @GraphQLArgument(name = "messageSpecification") MsgSpec messageSpecifications,
        @GraphQLRootContext RequestContext context) throws ServiceException {
       return messageRepository.getMessage(context, messageSpecifications);
    }
}


















