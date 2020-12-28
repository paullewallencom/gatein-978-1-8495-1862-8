package my.extension;

import org.exoplatform.services.organization.Query;

@SuppressWarnings("unchecked")
public class MyQuery extends Query {

	private static final long serialVersionUID = 3140401194984885131L;

		public MyQuery(String organization_) {
                this.organization_ = organization_;
        }
        
        private String organization_;

        public String getOrganization() {
                return organization_;
        }

        public void setOrganization(String s) {
                this.organization_ = s;
        }
}
