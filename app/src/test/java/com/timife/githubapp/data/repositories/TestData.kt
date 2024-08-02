package com.timife.githubapp.data.repositories

import com.timife.githubapp.data.mappers.toUserProfile
import com.timife.githubapp.data.model.repos.LicenseDto
import com.timife.githubapp.data.model.repos.OwnerDto
import com.timife.githubapp.data.model.repos.RepoDto
import com.timife.githubapp.data.model.userprofile.UserProfileDto
import com.timife.githubapp.data.model.users.SearchResponse
import com.timife.githubapp.data.model.users.UserDto
import com.timife.githubapp.domain.entities.userprofile.UserProfile
import com.timife.githubapp.domain.entities.users.User

object TestData {
    const val QUERY = "tim"
    private val fakeUserDto1 = UserDto(
        avatarUrl = "https://example.com/avatar.jpg",
        eventsUrl = "https://example.com/events",
        followersUrl = "https://example.com/followers",
        followingUrl = "https://example.com/following",
        gistsUrl = "https://example.com/gists",
        gravatarId = "",
        htmlUrl = "https://example.com/html",
        id = 12345,
        login = "fakeuser",
        nodeId = "MDQ6VXNlcjEyMzQ1",
        organizationsUrl = "https://example.com/orgs",
        receivedEventsUrl = "https://example.com/received_events",
        reposUrl = "https://example.com/repos",
        siteAdmin = false,
        starredUrl = "https://example.com/starred",
        subscriptionsUrl = "https://example.com/subscriptions",
        type = "User",
        url = "https://example.com",
        score = 100
    )

    private val fakeUserDto2 = UserDto(
        avatarUrl = "https://example.com/avatar.jpg",
        eventsUrl = "https://example.com/events",
        followersUrl = "https://example.com/followers",
        followingUrl = "https://example.com/following",
        gistsUrl = "https://example.com/gists",
        gravatarId = "",
        htmlUrl = "https://example.com/html",
        id = 12345,
        login = "fakeuser",
        nodeId = "MDQ6VXNlcjEyMzQ1",
        organizationsUrl = "https://example.com/orgs",
        receivedEventsUrl = "https://example.com/received_events",
        reposUrl = "https://example.com/repos",
        siteAdmin = false,
        starredUrl = "https://example.com/starred",
        subscriptionsUrl = "https://example.com/subscriptions",
        type = "User",
        url = "https://example.com",
        score = 100
    )

    val fakeUserProfileDto = UserProfileDto(
        avatarUrl = "https://avatars.githubusercontent.com/u/123456?v=4",
        bio = "This is a fake user profile bio.",
        blog = "https://fakeuserblog.com",
        company = "Fake Company Inc.",
        createdAt = "2020-01-01T00:00:00Z",
        email = "fakeuser@example.com",
        eventsUrl = "https://api.github.com/users/fakeuser/events{/privacy}",
        followers = 100,
        followersUrl = "https://api.github.com/users/fakeuser/followers",
        following = 50,
        followingUrl = "https://api.github.com/users/fakeuser/following{/other_user}",
        gistsUrl = "https://api.github.com/users/fakeuser/gists{/gist_id}",
        gravatarId = "",
        hireable = true,
        htmlUrl = "https://github.com/fakeuser",
        id = 123456,
        location = "Fake City, FS",
        login = "fakeuser",
        name = "Fake User",
        nodeId = "MDQ6VXNlcjEyMzQ1Ng==",
        organizationsUrl = "https://api.github.com/users/fakeuser/orgs",
        publicGists = 10,
        publicRepos = 20,
        receivedEventsUrl = "https://api.github.com/users/fakeuser/received_events",
        reposUrl = "https://api.github.com/users/fakeuser/repos",
        siteAdmin = false,
        starredUrl = "https://api.github.com/users/fakeuser/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/fakeuser/subscriptions",
        twitterUsername = "fakeuser",
        type = "User",
        updatedAt = "2023-01-01T00:00:00Z",
        url = "https://api.github.com/users/fakeuser"
    )

    val fakeUserRepoDto = listOf(
        RepoDto(
            allowForking = true,
            archiveUrl = "https://api.github.com/repos/fakeuser/fakerepo/{archive_format}{/ref}",
            archived = false,
            assigneesUrl = "https://api.github.com/repos/fakeuser/fakerepo/assignees{/user}",
            blobsUrl = "https://api.github.com/repos/fakeuser/fakerepo/git/blobs{/sha}",
            branchesUrl = "https://api.github.com/repos/fakeuser/fakerepo/branches{/branch}",
            cloneUrl = "https://github.com/fakeuser/fakerepo.git",
            collaboratorsUrl = "https://api.github.com/repos/fakeuser/fakerepo/collaborators{/collaborator}",
            commentsUrl = "https://api.github.com/repos/fakeuser/fakerepo/comments{/number}",
            commitsUrl = "https://api.github.com/repos/fakeuser/fakerepo/commits{/sha}",
            compareUrl = "https://api.github.com/repos/fakeuser/fakerepo/compare/{base}...{head}",
            contentsUrl = "https://api.github.com/repos/fakeuser/fakerepo/contents/{+path}",
            contributorsUrl = "https://api.github.com/repos/fakeuser/fakerepo/contributors",
            createdAt = "2023-01-01T00:00:00Z",
            defaultBranch = "main",
            deploymentsUrl = "https://api.github.com/repos/fakeuser/fakerepo/deployments",
            description = "This is a fake repository.",
            disabled = false,
            downloadsUrl = "https://api.github.com/repos/fakeuser/fakerepo/downloads",
            eventsUrl = "https://api.github.com/repos/fakeuser/fakerepo/events",
            fork = false,
            forks = 10,
            forksCount = 10,
            forksUrl = "https://api.github.com/repos/fakeuser/fakerepo/forks",
            fullName = "fakeuser/fakerepo",
            gitCommitsUrl = "https://api.github.com/repos/fakeuser/fakerepo/git/commits{/sha}",
            gitRefsUrl = "https://api.github.com/repos/fakeuser/fakerepo/git/refs{/sha}",
            gitTagsUrl = "https://api.github.com/repos/fakeuser/fakerepo/git/tags{/sha}",
            gitUrl = "git://github.com/fakeuser/fakerepo.git",
            hasDiscussions = true,
            hasDownloads = true,
            hasIssues = true,
            hasPages = false,
            hasProjects = true,
            hasWiki = true,
            homepage = "https://fakeuser.github.io/fakerepo/",
            hooksUrl = "https://api.github.com/repos/fakeuser/fakerepo/hooks",
            htmlUrl = "https://github.com/fakeuser/fakerepo",
            id = 123456789,
            isTemplate = false,
            issueCommentUrl = "https://api.github.com/repos/fakeuser/fakerepo/issues/comments{/number}",
            issueEventsUrl = "https://api.github.com/repos/fakeuser/fakerepo/issues/events{/number}",
            issuesUrl = "https://api.github.com/repos/fakeuser/fakerepo/issues{/number}",
            keysUrl = "https://api.github.com/repos/fakeuser/fakerepo/keys{/key_id}",
            labelsUrl = "https://api.github.com/repos/fakeuser/fakerepo/labels{/name}",
            language = "Kotlin",
            languagesUrl = "https://api.github.com/repos/fakeuser/fakerepo/languages",
            license = LicenseDto(key = "mit", name = "MIT License", spdxId = "MIT", url = "https://api.github.com/licenses/mit", nodeId = "MDc6TGljZW5zZW1pdA=="),
            mergesUrl = "https://api.github.com/repos/fakeuser/fakerepo/merges",
            milestonesUrl = "https://api.github.com/repos/fakeuser/fakerepo/milestones{/number}",
            mirrorUrl = null,
            name = "fakerepo",
            nodeId = "MDEwOlJlcG9zaXRvcnkxMjM0NTY3ODk=",
            notificationsUrl = "https://api.github.com/repos/fakeuser/fakerepo/notifications{?since,all,participating}",
            openIssues = 5,
            openIssuesCount = 5,
            owner = OwnerDto(login = "fakeuser", id = 123456, nodeId = "MDQ6VXNlcjEyMzQ1Ng==", avatarUrl = "https://avatars.githubusercontent.com/u/123456?v=4", gravatarId = "", url = "https://api.github.com/users/fakeuser", htmlUrl = "https://github.com/fakeuser", followersUrl = "https://api.github.com/users/fakeuser/followers", followingUrl = "https://api.github.com/users/fakeuser/following{/other_user}", gistsUrl = "https://api.github.com/users/fakeuser/gists{/gist_id}", starredUrl = "https://api.github.com/users/fakeuser/starred{/owner}{/repo}", subscriptionsUrl = "https://api.github.com/users/fakeuser/subscriptions", organizationsUrl = "https://api.github.com/users/fakeuser/orgs", reposUrl = "https://api.github.com/users/fakeuser/repos", eventsUrl = "https://api.github.com/users/fakeuser/events{/privacy}", receivedEventsUrl = "https://api.github.com/users/fakeuser/received_events", type = "User", siteAdmin = false),
            private = false,
            pullsUrl = "https://api.github.com/repos/fakeuser/fakerepo/pulls{/number}",
            pushedAt = "2023-06-01T00:00:00Z",
            releasesUrl = "https://api.github.com/repos/fakeuser/fakerepo/releases{/id}",
            size = 1234,
            sshUrl = "git@github.com:fakeuser/fakerepo.git",
            stargazersCount = 42,
            stargazersUrl = "https://api.github.com/repos/fakeuser/fakerepo/stargazers",
            statusesUrl = "https://api.github.com/repos/fakeuser/fakerepo/statuses/{sha}",
            subscribersUrl = "https://api.github.com/repos/fakeuser/fakerepo/subscribers",
            subscriptionUrl = "https://api.github.com/repos/fakeuser/fakerepo/subscription",
            svnUrl = "https://svn.github.com/fakeuser/fakerepo",
            tagsUrl = "https://api.github.com/repos/fakeuser/fakerepo/tags",
            teamsUrl = "https://api.github.com/repos/fakeuser/fakerepo/teams",
            topics = listOf("kotlin", "example", "repository"),
            treesUrl = "https://api.github.com/repos/fakeuser/fakerepo/git/trees{/sha}",
            updatedAt = "2023-07-01T00:00:00Z",
            url = "https://api.github.com/repos/fakeuser/fakerepo",
            visibility = "public",
            watchers = 42,
            watchersCount = 42,
            webCommitSignoffRequired = false
        )
    )

    val userApiResponse = SearchResponse(
        1, true, listOf(
            fakeUserDto1, fakeUserDto2
        )
    )
    val fakeFollowersApiResponse = listOf(
        fakeUserDto1
    )

    val fakeFollowingsApiResponse = listOf(
        fakeUserDto2
    )


    val expectedEmittedUsers = listOf(
        User(
            fakeUserDto1.avatarUrl,
            fakeUserDto1.followersUrl,
            fakeUserDto1.followingUrl,
            fakeUserDto1.id,
            fakeUserDto1.login,
            fakeUserDto1.reposUrl,
            fakeUserDto1.url
        ),
        User(
            fakeUserDto2.avatarUrl,
            fakeUserDto2.followersUrl,
            fakeUserDto2.followingUrl,
            fakeUserDto2.id,
            fakeUserDto2.login,
            fakeUserDto2.reposUrl,
            fakeUserDto2.url
        )
    )

    val expectedEmittedProfile = fakeUserProfileDto.toUserProfile()

}
