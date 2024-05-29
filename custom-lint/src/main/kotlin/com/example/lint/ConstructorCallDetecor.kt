package com.example.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UastCallKind
import org.jetbrains.uast.getQualifiedName

val ConstructorCallDetectorIssue = Issue.create(
    id = "ConstructorCallDetectorIssue",
    briefDescription = "",
    explanation = "",
    implementation = Implementation(ConstructorCallDetector::class.java, Scope.JAVA_FILE_SCOPE),
    severity = Severity.FATAL,
)

class ConstructorCallDetector : Detector(), SourceCodeScanner {

    override fun getApplicableConstructorTypes(): List<String> =
        listOf(
            "com.example.mylibrary.Foo",
            "com.example.mylibrary.FooWithGeneric",
            "com.example.lint_k2_constructor_call.Baz",
            "com.example.lint_k2_constructor_call.BazWithGeneric",
        )

    override fun visitConstructor(
        context: JavaContext,
        node: UCallExpression,
        constructor: PsiMethod
    ) {
        context.report(
            issue = ConstructorCallDetectorIssue,
            location = context.getLocation(node),
            scope = node,
            message = "Constructor call is detected"
        )
    }

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {
        override fun visitCallExpression(node: UCallExpression) {
            if (node.kind != UastCallKind.CONSTRUCTOR_CALL) {
                return
            }
            println("Class \"${node.classReference.getQualifiedName()}\"")
            println("Resolved constructor PSI: ${node.resolve()}")
        }
    }

    override fun getApplicableUastTypes() = listOf(UCallExpression::class.java)
}