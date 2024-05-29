@file:Suppress("UnstableApiUsage")

package com.example.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class IssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(
            ConstructorCallDetectorIssue
        )

    override val api: Int get() = CURRENT_API

    override val vendor = Vendor()
}
