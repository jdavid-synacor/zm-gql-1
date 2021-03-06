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

import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.impl.ZXMLFolderRepository;
import com.zimbra.soap.mail.type.Folder;
import com.zimbra.soap.mail.type.FolderActionResult;
import com.zimbra.soap.mail.type.FolderActionSelector;
import com.zimbra.soap.mail.type.GetFolderSpec;
import com.zimbra.soap.mail.type.ModifySearchFolderSpec;
import com.zimbra.soap.mail.type.NewFolderSpec;
import com.zimbra.soap.mail.type.NewSearchFolderSpec;
import com.zimbra.soap.mail.type.SearchFolder;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The FolderResolver class.<br>
 * Contains folder schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class FolderResolver {

    protected ZXMLFolderRepository folderRepository = null;

    /**
     * Creates an instance with specified folder repository.
     *
     * @param folderRepository The folder repository
     */
    public FolderResolver(ZXMLFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @GraphQLQuery(description = "Retrieve a folder by given properties.")
    public Folder folder(@GraphQLArgument(name = "visible") Boolean visible,
        // default to false because the spec uses boolean
        @GraphQLArgument(name = "needGranteeName", defaultValue = "false") Boolean needGranteeName,
        @GraphQLArgument(name = "view") Folder.View view,
        @GraphQLArgument(name = "depth") Integer depth,
        @GraphQLArgument(name = "traverseMountpoints") Boolean traverseMountpoints,
        @GraphQLArgument(name = "getFolder") GetFolderSpec getFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.folder(context, visible, needGranteeName, view, depth,
            traverseMountpoints, getFolder);
    }

    @GraphQLMutation(description = "Create a folder with given properties.")
    public Folder folderCreate(
        @GraphQLNonNull @GraphQLArgument(name = "folder") NewFolderSpec folder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.createFolder(context, folder);
    }

    @GraphQLMutation(description = "Handles a folder action request.")
    public FolderActionResult folderAction(
        @GraphQLNonNull @GraphQLArgument(name = "input") FolderActionSelector input,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.action(context, input);
    }

    @GraphQLQuery(description = "Retrieve search folders for given user")
    public List<SearchFolder> searchFolderGet(@GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderGet(context);
    }

    @GraphQLMutation(description = "Create a search folder with given properties.")
    public SearchFolder searchFolderCreate(
        @GraphQLNonNull @GraphQLArgument(name = "searchFolder") NewSearchFolderSpec searchFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderCreate(context, searchFolder);
    }

    @GraphQLMutation(description = "Modify existing search folder with given properties.")
    public SearchFolder searchFolderModify(
        @GraphQLNonNull @GraphQLArgument(name = "searchFolder") ModifySearchFolderSpec searchFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderModify(context, searchFolder);
    }
}