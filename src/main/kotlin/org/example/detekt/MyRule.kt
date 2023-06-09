package org.example.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.rules.isInternal
import org.jetbrains.kotlin.psi.KtClass

class MyRule(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Classes should have the internal modifier.",
        Debt.FIVE_MINS,
    )

    override fun visitClass(klass: KtClass) {
        println("Class ${klass.nameAsSafeName} visited.")

        if (klass.isInternal()) return
        else report(CodeSmell(issue, Entity.atName(klass), "Class ${klass.nameAsSafeName} should have the 'internal' modifier."))
    }
}
